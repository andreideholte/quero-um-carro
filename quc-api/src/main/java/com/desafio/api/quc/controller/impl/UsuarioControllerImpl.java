package com.desafio.api.quc.controller.impl;

import com.desafio.api.quc.controller.UsuarioController;
import com.desafio.api.quc.document.Usuario;
import com.desafio.api.quc.service.UsuarioService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Usuário")
@RequestMapping("/usuario")
@RestController
public class UsuarioControllerImpl implements UsuarioController {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
	private UsuarioService usuarioService;

	@Override
	@ApiOperation(value = "Criar um usuario")
	public ResponseEntity<Usuario> criar(Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@ApiOperation(value = "Buscar um usuario pelo email")
	public ResponseEntity<Usuario> buscarPorEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@ApiOperation(value = "Atualizar um usuario")
	public ResponseEntity<Usuario> atualizar(Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}

  /*   @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{email}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Person get(@PathVariable(value = "email") String email){
        Person person = personService.findById(personId);
        addHATEOASSupport(person, personId);
		return person;
    }
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/findAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Person> findAll(){
		List<Person> persons = personService.findAll();
		ArrayList<Person> personsReturn = new ArrayList<Person>();
		for (Person person : persons) {
			String idPerson = person.getIdPerson().toString();
			addHATEOASSupport(person, idPerson);
			personsReturn.add(person);
		}
		return personsReturn;
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Person create(@RequestBody Person person){
		Person createdPerson = personService.create(person);
		String idPerson = createdPerson.getIdPerson().toString();
		addHATEOASSupport(createdPerson, idPerson);
		return createdPerson;
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.PUT,consumes = MediaType.APPLICATION_JSON_VALUE)
	public Person update(@RequestBody Person person){
		Person updatedPerson = personService.update(person);
		String idPerson = updatedPerson.getIdPerson().toString();
		addHATEOASSupport(updatedPerson, idPerson);		
		return updatedPerson;
	}

	@ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{personId}", method = RequestMethod.DELETE)
    public void delete(@PathVariable(value = "personId") String personId){
        personService.delete(personId);
    }

	private void addHATEOASSupport(Person person, String idPerson) {
		person.add(linkTo(methodOn(PersonController.class).get(idPerson)).withSelfRel());
	} */
	
}