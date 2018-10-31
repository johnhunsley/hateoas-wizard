package com.johnhunsley.hateoas.wizard;

import com.johnhunsley.hateoas.wizard.api.WizardController;
import com.johnhunsley.hateoas.wizard.model.AbstractStep;
import com.johnhunsley.hateoas.wizard.model.Step;
import com.johnhunsley.hateoas.wizard.repository.StepRepositoryJpaImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * <p>
 *
 * </p>
 * @author jphunsley@gmail.com
 */
@Component
public class Wizard {

    @Autowired
    private StepRepositoryJpaImpl stepRepository;

    /**
     * <p>
     *      Gets the {@link AbstractStep} type with the given stepNumber which will include a link to the previous
     *      step if the given stepNumber is not the first and will include a link to the next step if the
     *      given stepNumber is not the last and is complete.
     * </p>
     * @param stepNumber
     * @return
     */
    public ResponseEntity<AbstractStep> getStep(final int stepNumber) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        AbstractStep returnable;

        //is previous step complete? If not throw HTTP 412
        if(stepNumber > 0) {
            if(stepRepository.findById(stepNumber - 1).isPresent()) {
                AbstractStep previousStep = stepRepository.getOne(stepNumber - 1);

                if(!previousStep.isComplete()) return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
            }
        }


        if(stepRepository.findById(stepNumber).isPresent()) {
            returnable = stepRepository.getOne(stepNumber);

            if(returnable.isComplete()) {
                //include the next link if stepNumber < totalSteps -1

                if(stepNumber < stepRepository.getMaxStep()) {
                    returnable.add(linkTo(methodOn(WizardController.class).getStep(stepNumber+1)).withRel(Step.NEXT.name()));

                }


            }

            //add back link if step Number > 0
            if(stepNumber > 0) {
                returnable.add(linkTo(methodOn(WizardController.class).getStep(stepNumber-1)).withRel(Step.PREVIOUS.name()));
            }

            returnable.add(linkTo(methodOn(WizardController.class).getStep(stepNumber)).withSelfRel());
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<AbstractStep> getNextStep(final int nextStepNumber) {
        //if no persistent step with the given number doesnt exist, create a new one

    }
}
