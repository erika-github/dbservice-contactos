/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bod.empresas.contactos.repository;

import com.bod.empresas.contactos.entity.Contactos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author eadiaz
 */
@Transactional
public interface ContactoRepository extends JpaRepository<Contactos, Integer> {

    /*@Query(
            value ="select * from contactos u where u.identificacion = ?1 and u.tipo_identificacion = ?2",
     nativeQuery = true)       
    Contactos findByIdentificacionContacto(String identificacions, char tipo);*/
 /*@Query("select u from Contactos u where u.identificacion = :numDocContacto and u.tipo_identificacion = :tipoDocContacto")
    Contactos findByIdentificacionContacto(@Param("numDocContacto") String numDocCliente, @Param("tipoDocContacto") char tipo);*/
    @Query("SELECT u FROM Contactos u WHERE u.identificacion = :identificacion and u.tipoIdentificacion = :tipoIdentificacion")
    Contactos findByIdentificacionContacto(@Param("identificacion") String identificacion,
            @Param("tipoIdentificacion") char tipoIdentificacion);

}
