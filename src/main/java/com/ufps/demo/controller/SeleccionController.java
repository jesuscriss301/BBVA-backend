/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufps.mundial.controller;


import com.ufps.mundial.model.Seleccion;
import com.ufps.mundial.repository.SeleccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/selecciones")
public class SeleccionController {
    @Autowired
    SeleccionRepository seleccionRepo;

    @GetMapping
    public List<Seleccion> getSeleccionAll() {
        return seleccionRepo.findAll();
    }

    @GetMapping("/{id}")
    public Seleccion getSeleccionsbyId(@PathVariable Integer id) {
        Optional<Seleccion> seleccion = seleccionRepo.findById(id);

        if (seleccion.isPresent()) {
            return seleccion.get();
        }

        return null;
    }

    @PostMapping
    public Seleccion postSeleccions(@RequestBody Seleccion seleccion) {
        seleccionRepo.save(seleccion);
        return seleccion;
    }


    @PutMapping("/{id}")
    public Seleccion putSeleccionsbyId(@PathVariable Integer id, @RequestBody Seleccion seleccion) {
        Optional<Seleccion> seleccionCurrent = seleccionRepo.findById(id);

        if (seleccionCurrent.isPresent()) {
            Seleccion seleccionReturn = seleccionCurrent.get();
            seleccionReturn.setNombre(seleccion.getNombre());
            seleccionReturn.setContinente_id(seleccion.getContinente_id());
            seleccionReturn.setGrupo(seleccion.getGrupo());

            seleccionRepo.save(seleccionReturn);
            return seleccionReturn;
        }

        return null;
    }

    @DeleteMapping("/{id}")
    public Seleccion deleteSeleccionsbyId(@PathVariable Integer id) {
        Optional<Seleccion> seleccion = seleccionRepo.findById(id);

        if (seleccion.isPresent()) {
            Seleccion seleccionReturn = seleccion.get();
            seleccionRepo.deleteById(id);
            return seleccionReturn;
        }

        return null;
    }
}
