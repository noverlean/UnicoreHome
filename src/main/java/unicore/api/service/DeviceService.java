package unicore.api.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import unicore.api.dto.devices.lightning.LightningDto;
import unicore.api.dto.devices.lightning.SyncLightning;
import unicore.api.dto.devices.switch_.SwitchDto;
import unicore.api.dto.devices.switch_.SyncSwitch;
import unicore.api.entities.*;
import unicore.api.mappers.LightningMapper;
import unicore.api.mappers.SwitchMapper;
import unicore.api.repository.*;
import unicore.api.utils.CodeGenerator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeviceService {
    private final UserService userService;
    private final UserRepository userRepository;
    private final EnvironmentRepository environmentRepository;
    private final DeviceRepository deviceRepository;

    private final LightningRepository lightningRepository;
    private final SwitchRepository switchRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private LightningMapper lightningMapper;
    @Autowired
    private SwitchMapper switchMapper;

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public List<Environment> findByName(String name) {
        return environmentRepository.findByEmail(name);
    }

    @Transactional
    public ResponseEntity<User> addDevice(String email, String deviceName, String deviceType, String deviceColor, String deviceIP) {
        User user = userService.getUser(email);
        Environment environment = user.getEnvironment();
        if (environment == null || environment.getDevices().stream().anyMatch(x -> x.getName().equals(deviceName))) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Device device = new Device(
                deviceName,
                CodeGenerator.generateCode(),
                deviceColor,
                environment,
                deviceIP,
                new Date()
        );
        deviceType = deviceType.toLowerCase();
        switch (deviceType)
        {
            case "led line" -> {
                device.setLightning(lightningRepository.save(new Lightning(deviceType, "WS2811", 40)));
            }
            case "ambilight" -> {
                device.setLightning(lightningRepository.save(new Lightning(deviceType, "WS2811", 10, 10, 10, 10)));
            }
            case "switch" -> {
                device.setSwitch_(switchRepository.save(new Switch()));
            }
            default -> throw new IllegalStateException("Unexpected value: " + device);
        };
        device.print();
        device = deviceRepository.save(device);
        entityManager.flush();

        user.getEnvironment().getDevices().add(device);
        user = userRepository.save(user);
        return ResponseEntity.ok(user);
    }

    @Transactional
    public ResponseEntity<User> removeDevice(String email, String deviceName) {
        User user = userService.getUser(email);
        Optional<Device> device = deviceRepository.findByName(email, deviceName);
        if (device.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        deviceRepository.delete(device.get());
        entityManager.flush();

        user.getEnvironment().getDevices().remove(device.get());
        user = userRepository.save(user);
        return ResponseEntity.ok(user);
    }

//    @Transactional
//    public ResponseEntity<User> setIP(String email, String deviceName, String deviceIP) {
//        User user = userService.getUser(email);
//        Device device = deviceRepository.findByName(email, deviceName).orElse(null);
//        if (device == null)
//        {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//
//        device.setIp(deviceIP);
//        device = deviceRepository.save(device);
//        entityManager.flush();
//
//        // Обновляем связанные сущности в объекте пользователя
//        user.getEnvironment().getDevices().remove(device);
//        user.getEnvironment().getDevices().add(device);
//        user = userRepository.save(user);
//        return ResponseEntity.ok(user);
//    }

    public ResponseEntity<User> setLightning(String email, LightningDto lightningDto) throws ParseException {
        User user = userService.getUser(email);
        Device device = deviceRepository.findByName(email, lightningDto.getName()).orElse(null);
        if (device == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Lightning lightning = lightningMapper.dtoToModel(lightningDto);
        lightningRepository.save(lightning); // Сохраняем экземпляр Lightning перед его использованием

        device.setLightning(lightning);
        device.setChangeTime(format.parse(lightningDto.getChangeTime()));
        deviceRepository.save(device);

        User updatedUser = userService.getUser(email);
        return ResponseEntity.ok(updatedUser);
    }

    public ResponseEntity<SyncLightning> synchronizeLightning_LOOP(SyncLightning syncLightning) throws ParseException {
        Device device = deviceRepository.findByName(syncLightning.getEmail(), syncLightning.getDevice_name()).orElse(null);
        if (device == null || !device.getAccess_code().equals(syncLightning.getAccess_code())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Date boardLastUpdate = syncLightning.getChangeTime() != "" ? format.parse(syncLightning.getChangeTime()) : new Date(0);
        int comparison = device.getChangeTime().compareTo(boardLastUpdate);

        if (comparison < 0){ // if DB data is NOT actually
            device.setLightning(lightningMapper.dtoToModel(syncLightning.getAsLightningDto()));
            device.setIp(syncLightning.getIp());
            device.setChangeTime(boardLastUpdate);

            deviceRepository.save(device);
        } else {
            syncLightning.setAsLightningDto(lightningMapper.modelToDto(device.getLightning()));
//            syncLightning.setIp(device.getIp());
            syncLightning.setChangeTime(format.format(device.getChangeTime()));
        }

        return ResponseEntity.ok(syncLightning);
    }

    public ResponseEntity<User> setSwitch(String email, SwitchDto switchDto) throws ParseException {
        User user = userService.getUser(email);
        Device device = deviceRepository.findByName(email, switchDto.getName()).orElse(null);
        if (device == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Switch switch_ = switchMapper.dtoToModel(switchDto);
        switchRepository.save(switch_); // Сохраняем экземпляр Switch перед его использованием

        device.setSwitch_(switch_);
        device.setChangeTime(format.parse(switchDto.getChangeTime()));
        deviceRepository.save(device);

        User updatedUser = userService.getUser(email);
        return ResponseEntity.ok(updatedUser);
    }

    public ResponseEntity<SyncSwitch> synchronizeSwitch_LOOP(SyncSwitch syncSwitch) throws ParseException {
        Device device = deviceRepository.findByName(syncSwitch.getEmail(), syncSwitch.getDevice_name()).orElse(null);
        if (device == null || !device.getAccess_code().equals(syncSwitch.getAccess_code())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Date boardLastUpdate = syncSwitch.getChangeTime() != "" ? format.parse(syncSwitch.getChangeTime()) : new Date(0);
        int comparison = device.getChangeTime().compareTo(boardLastUpdate);

        if (comparison < 0){ // if DB data is NOT actually
            Switch savedSwitch = switchRepository.save(switchMapper.dtoToModel(syncSwitch.getAsSwitchDto()));
            device.setSwitch_(savedSwitch);
            device.setIp(syncSwitch.getIp());
            device.setChangeTime(boardLastUpdate);

            deviceRepository.save(device);
        } else {
            syncSwitch.setAsSwitchDto(switchMapper.modelToDto(device.getSwitch_()));
//            syncSwitch.setIp(device.getIp());
            syncSwitch.setChangeTime(format.format(device.getChangeTime()));
        }

        return ResponseEntity.ok(syncSwitch);
    }

    public ResponseEntity<Device> getDevice(String email, String deviceName, String accessCode) {
        Optional<Device> device = deviceRepository.findByName(email, deviceName);
        if (device.isEmpty() || !device.get().getAccess_code().equals(accessCode))
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(device.get());
    }
}
