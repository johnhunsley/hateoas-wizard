package com.johnhunsley.hateoas.wizard.api;

import com.johnhunsley.hateoas.wizard.Wizard;
import com.johnhunsley.hateoas.wizard.model.AbstractStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;

/**
 * <p>
 *
 * </p>
 * @author jphunsley@gmail.com
 */
@RestController
@RequestMapping("wizard/")
public class WizardController {

    @Autowired
    private Wizard wizard;

    @CrossOrigin
    @RequestMapping(value = "{step}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<AbstractStep> getStep(@PathVariable("step") final int stepNumber) {
        try {
            return wizard.getStep(stepNumber);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "{nextStep}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<AbstractStep> nextStep(@PathVariable("nextStep") final int nextStepNumber,
                                                 @RequestBody AbstractStep step) {
        try {
            if(!step.isComplete()) return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);

            wizard.getNextStep(nextStepNumber);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
