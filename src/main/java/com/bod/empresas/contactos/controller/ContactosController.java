/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bod.empresas.contactos.controller;

import com.bod.empresas.contactos.common.model.Response;
import com.bod.empresas.contactos.entity.Contactos;
import com.bod.empresas.contactos.service.ContactosService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author eadiaz
 */
@RestController
@RefreshScope
@RequestMapping(value = "/", headers = "Accept=application/json")
public class ContactosController {
    
    @Autowired
    ContactosService contactosService;  
    
    @RequestMapping(method = RequestMethod.POST, path = "getContactoByDocIdent",
            consumes = "application/json",
            produces = "application/json")
    //@PostMapping("getContacto")
    public ResponseEntity<?> getContactoByDocIdent(@RequestBody Contactos contacto) {
        //logger.info("-- UsuarioController.getUsuario: ");
        System.out.println("Prueba:::"+ contacto.getIdentificacion());
        Response response=contactosService.getContactoByDocIdent(contacto.getIdentificacion(), contacto.getTipoIdentificacion());  
        return ResponseEntity.ok().body(response);
    }
    
    
    @RequestMapping(method = RequestMethod.POST, path = "getContactoById",
            consumes = "application/json",
            produces = "application/json")    
   
    public ResponseEntity<?> getContactoById(@RequestBody Contactos contacto) {
        //logger.info("-- UsuarioController.crearUsuario: ",contacto);
        Response response=contactosService.getContactoById(contacto.getId());
        return ResponseEntity.ok().body(response);
    }
    
    
    @PostMapping("getAllContacts")
    public ResponseEntity<?> getAllContactos() {
        //logger.info("-- UsuarioController.crearUsuario: ",contacto);
        Response response=contactosService.getAllContacts();
        return ResponseEntity.ok().body(response);
    }
    
    @PostMapping("createContacto")
    public ResponseEntity<?> createContacto(@RequestBody Contactos contacto) {
        //logger.info("-- UsuarioController.crearUsuario: ",contacto);
        Response response=contactosService.createContacto(contacto);
        return ResponseEntity.ok().body(response);
    }
    
    @PostMapping("updateContacto")
    public ResponseEntity<?> updateContacto(@RequestBody Contactos contacto) {
        //logger.info("-- UsuarioController.crearUsuario: ",contacto);
        Response response=contactosService.updateContacto(contacto);
        return ResponseEntity.ok().body(response);
    }
    
    @PostMapping("deleteContactoById")
    public ResponseEntity<?> delteContactoById(@RequestBody Contactos contacto) {
        //logger.info("-- UsuarioController.crearUsuario: ",contacto);
        System.out.println("Prueba:::"+ contacto.getId());
        Response response=contactosService.deleteContactoById(contacto.getId());
        return ResponseEntity.ok().body(response);
    }
    
    

    
}
