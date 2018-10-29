# hateoas-wizard
A Wizard implemented as a stateless REST API using HATEOAS principle to manage the representation of state in the Wizard

The simple abstract model, using BeanUtils and introspection, to define state as having all attributes completed. This allows the model to be extended and applied to any Wizard use-case. When completed, the client can move forward to the next resource, or back to any of the other resources defined in the sequence of the Wizard. 

Spring HATEAOS provides the links to the sequenced resources based on the state of the model. i.e. if a resource is completed, it will include links to itself, its previous and its next resource in the sequence of the Wizard. If it is not completed, it will not provide a link to the next resource.

![HATEAOS Wizard](https://github.com/johnhunsley/hateoas-wizard/HATHEAOS%20Wizard.png "HATEAOS Wizard") 
