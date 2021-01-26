package ua.polina.api_gateway.service;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

/**
 * It is called when the response message received.
 */
@Service
@Slf4j
public class EndProcessService implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) {
        String personDto = (String) delegateExecution.getVariable("receivedMessage");
        log.info(personDto);
    }
}
