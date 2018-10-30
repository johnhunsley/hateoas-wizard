package com.johnhunsley.hateoas.wizard.api;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import com.johnhunsley.hateoas.wizard.model.AbstractStep;
import com.johnhunsley.hateoas.wizard.repository.StepRepositoryJpaImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    private StepRepositoryJpaImpl stepRepository;

    @CrossOrigin
    @RequestMapping(value = "{step}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<AbstractStep> getStep(@PathVariable("step") final int stepNumber) {
        AbstractStep step = stepRepository.getOne(stepNumber);

        //is previous step complete? If not throw HTTP 412

        if(step.isComplete())  {
            //include the next link if stepNumber < totalSteps -1
        }

        //add back link if step Number > 0




        return new ResponseEntity<>(HttpStatus.OK);
    }
}
