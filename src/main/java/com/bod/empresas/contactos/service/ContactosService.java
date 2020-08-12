/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bod.empresas.contactos.service;

import com.bod.empresas.contactos.common.model.Response;
import com.bod.empresas.contactos.entity.Contactos;
import com.bod.empresas.contactos.repository.ContactoRepository;
import java.util.Date;
import java.util.List;
import java.util.Optional;
//import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author eadiaz
 */
@Service
public class ContactosService {

    @Autowired
    ContactoRepository contactoRepository;

    public Response getContactoByDocIdent(String identificacion, char tipoIdentificacion) {
        //logger.debug("Buscando el usuario "+usuario);

        Contactos contacto = contactoRepository.findByIdentificacionContacto(identificacion, tipoIdentificacion);

        Response responseModel = new Response();

        if (contacto != null) {
            responseModel.setMessage("OK");
            responseModel.setStatus(200);
            responseModel.setCode(0);
            responseModel.setData(contacto);
        } else {
            responseModel.setMessage("Contacto no encontrado");
            responseModel.setStatus(200);
            responseModel.setCode(1);
            responseModel.setData(null);
        }
        return responseModel;

    }

    public Response getContactoById(Integer id) {
        //logger.debug("Buscando el usuario "+usuario);

        Optional<Contactos> opt = contactoRepository.findById(id);

        Response responseModel = new Response();

        if (opt.isPresent()) {
            responseModel.setMessage("OK");
            responseModel.setStatus(200);
            responseModel.setCode(0);
            responseModel.setData(opt.get());
        } else {
            responseModel.setMessage("Contacto no encontrado");
            responseModel.setStatus(200);
            responseModel.setCode(1);
            responseModel.setData(null);
        }
        return responseModel;

    }

    public Response getAllContacts() {
        //logger.debug("Buscando el usuario "+usuario);
        Response responseModel = new Response();

        try {

            List<Contactos> contactos = contactoRepository.findAll();

            if (contactos.get(0).getId() != null) {
               
                responseModel.setMessage("OK");
                responseModel.setStatus(200);
                responseModel.setCode(0);
                responseModel.setData(contactos);
            }
        
        //El catch captura la excepcion (Indice fuera de los limites de la lista). Esto
        //ocurre cuando no consigue registros en la tabla de contactos
        } catch (IndexOutOfBoundsException e){

            responseModel.setMessage("No existen registros en la tabla de contactos");
            responseModel.setStatus(200);
            responseModel.setCode(1);
            responseModel.setData(null);

        }

        return responseModel;
    }

    public Response createContacto(Contactos contacto) {

        if (contacto.getFechaCreacion() == null) {
            contacto.setFechaCreacion(new Date());
        }

        Contactos object = contactoRepository.saveAndFlush(contacto);

        Response responseModel = new Response();

        responseModel.setMessage("Contacto creado con exito");
        responseModel.setStatus(200);
        responseModel.setCode(0);
        responseModel.setData(object.getId());

        return responseModel;
    }

    public Response updateContacto(Contactos contacto) {
        //logger.debug("Actualizando la session del. usuario "+session.getUsuario());
        //usuarioRepository.updateSession(session.getEstatus(), session.getUsuario(), session.getOauth());
        if (contacto.getFechaModificaion() == null) {
            contacto.setFechaModificaion(new Date());
        }

        contactoRepository.saveAndFlush(contacto);

        Response responseModel = new Response();
        responseModel.setMessage("Contacto modificado con exito");
        responseModel.setStatus(200);
        responseModel.setCode(0);
        responseModel.setData(null);
        return responseModel;
    }

    public Response deleteContactoById(Integer id) {
        
        //Primero realiza una consulta en la tabla de contactos         
        
        Optional<Contactos> opt = contactoRepository.findById(id);        
        

        Response responseModel = new Response();

        //Si el registro está presente en la tabla de contactos borra el registro en la misma.
        if (opt.isPresent()) {
            contactoRepository.deleteById(id);
            responseModel.setMessage("Contacto eliminado con exito");
            responseModel.setStatus(200);
            responseModel.setCode(0);
            responseModel.setData(null);
          
        //Si el registro NO está presente, se setea los valores al objeto responseModel, para que 
        //devuelva una respuesta positiva y evitar que sea mostrado un error 500 (Internal Server Error).
        } else {
            responseModel.setMessage("El registro a ser eliminado no existe en la tabla de contactos");
            responseModel.setStatus(200);
            responseModel.setCode(0);
            responseModel.setData(null);
        }
        return responseModel;

    }
}
