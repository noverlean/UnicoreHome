package unicore.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import unicore.api.api.DevicesApi;
import unicore.api.dto.EmailDto;
import unicore.api.dto.devices.AddDeviceDto;
import unicore.api.dto.devices.GetDeviceDto;
import unicore.api.dto.devices.RemoveDeviceDto;
import unicore.api.dto.devices.lightning.LightningDto;
import unicore.api.dto.devices.lightning.SyncLightning;
import unicore.api.dto.devices.switch_.SwitchDto;
import unicore.api.dto.devices.switch_.SyncSwitch;
import unicore.api.dto.environments.AddExitEnvironmentRequest;
import unicore.api.dto.environments.ConfirmAccessDto;
import unicore.api.dto.environments.CreateEnvironment;
import unicore.api.entities.Device;
import unicore.api.entities.User;
import unicore.api.service.impl.DeviceServiceImpl;
import unicore.api.service.impl.EnvironmentServiceImpl;

import java.security.Principal;
import java.text.ParseException;

@RestController
@RequiredArgsConstructor
public class DevicesController implements DevicesApi {
    private final EnvironmentServiceImpl environmentService;
    private final DeviceServiceImpl deviceService;

    public ResponseEntity<User> createEnvironment(@RequestBody CreateEnvironment createRequest, Principal principal) {
        System.out.println(createRequest);
        return environmentService.createNewEnvironment(principal.getName(), createRequest.getEnvironment_name());
    }

    public ResponseEntity<EmailDto> addToEnvironmentRequest(@RequestBody AddExitEnvironmentRequest addRequest, Principal principal) {
        System.out.println(addRequest);
        return environmentService.addToEnvironmentRequest(principal.getName(), addRequest.getEnvironment_name(), addRequest.getEnvironment_email());
    }

    public ResponseEntity<User> exitFromEnvironment(@RequestBody AddExitEnvironmentRequest exitRequest, Principal principal) {
        System.out.println(exitRequest);
        return environmentService.exitFromEnvironmentRequest(principal.getName(), exitRequest.getEnvironment_name(), exitRequest.getEnvironment_email());
    }

    public ResponseEntity<User> addToEnvironmentConfirmation(@RequestBody ConfirmAccessDto confirmAccessDto, Principal principal) {
        System.out.println(confirmAccessDto);
        return environmentService.addToEnvironmentConfirm(principal.getName(), confirmAccessDto.getEmail(), confirmAccessDto.getName(), confirmAccessDto.getCode());
    }

    public ResponseEntity<User> addDevice(@RequestBody AddDeviceDto addDeviceDto, Principal principal) {
        System.out.println(addDeviceDto);
        return deviceService.addDevice(principal.getName(), addDeviceDto.getDevice_name(), addDeviceDto.getDevice_type(), addDeviceDto.getDevice_color(), addDeviceDto.getIp());
    }

    public ResponseEntity<User> removeDevice(@RequestBody RemoveDeviceDto removeDeviceDto, Principal principal) {
        System.out.println(removeDeviceDto);
        return deviceService.removeDevice(principal.getName(), removeDeviceDto.getDevice_name());
    }

    public ResponseEntity<Device> getDevice(@RequestBody GetDeviceDto getDeviceDto) {
        System.out.println(getDeviceDto);
        return deviceService.getDevice(getDeviceDto.getEmail(), getDeviceDto.getDevice_name(), getDeviceDto.getAccess_code());
    }

    public ResponseEntity<User> setLightning(@RequestBody LightningDto lightningDto, Principal principal) throws ParseException {
        System.out.println(lightningDto);
        return deviceService.setLightning(principal.getName(), lightningDto);
    }

    public ResponseEntity<SyncLightning> synchronizeLightning_LOOP(@RequestBody SyncLightning syncLightning) throws ParseException {
        System.out.println(syncLightning);
        return deviceService.synchronizeLightning_LOOP(syncLightning);
    }

    public ResponseEntity<User> setSwitch(@RequestBody SwitchDto switchDto, Principal principal) throws ParseException {
        System.out.println(switchDto);
        return deviceService.setSwitch(principal.getName(), switchDto);
    }

    public ResponseEntity<SyncSwitch> synchronizeSwitch_LOOP(@RequestBody SyncSwitch syncSwitch) throws ParseException {
        System.out.println(syncSwitch);
        return deviceService.synchronizeSwitch_LOOP(syncSwitch);
    }
}