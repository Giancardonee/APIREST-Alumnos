package com.Gianca.RESTapi.controllers;
import com.Gianca.RESTapi.domain.Alumno;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController

// RequestMapping, sirve para unificar rutas a nivel de clase.
@RequestMapping("/alumnos")

public class AlumnoRestController {

    /*  Aclaraciones sobre el controlador:

        No es lo ideal trabajar con estructuras dinámicas como listas, ya que la información solo se mantiene durante la ejecucion
        Lo ideal sería persistir los datos en un almacenamiento permanente, como una base de datos.
        voy a usar estructuras dinámicas para practicar los metodos http de manera sencilla. 

        Tener la lógica de negocio dentro de los controladores no seria considerado una buena practica.
        En aplicaciones bien diseñadas, los controladores deben ser responsables de manejar solicitudes HTTP, delegar tareas a las capas adecuadas y devolver las respuestas al cliente.
        La lógica de negocio debe estar en servicios o capas especializadas.

        Dado que estoy aprendiendo Spring Boot, por ahora no voy a implementar persistencia en base de datos y tampoco separar la logica de negocio.

        El objetivo de este proyecto es practicar solicitudes HTTP

        En cada metodo retorno un Alumno, para verificar en PostMan que los metodos funcionen correctamente.
     */
    private List<Alumno> listaAlumnos = new ArrayList(Arrays.asList(
            new Alumno(123,"5 B",16,"David","david@gmail.com"),
            new Alumno(108,"5 A",17,"Gianca","gianca@gmail.com"),
            new Alumno(89,"6 A",18,"Leo","leo@gmail.com"),
            new Alumno(77,"5B",16,"Lautaro","lautaro@gmail.com")
    ));

    @GetMapping
    public List<Alumno> getListaAlumnos()
    {
        return this.listaAlumnos;
    }

    @GetMapping("/id/{id}")
    public Alumno getAlumnoPorID(@PathVariable int id)
    {
        return this.devolverAlumnoPorId(id);
    }

    @GetMapping("/email/{email}")
    public Alumno getAlumnoPorEmail(@PathVariable String email)
    {
        return this.listaAlumnos.stream()
                .filter(alumno -> alumno.getEmail().equals(email))
                .findFirst().orElse(null);
    }

    @PostMapping
    public Alumno postAlumno(@RequestBody Alumno alumno)
    {
        this.listaAlumnos.add(alumno);
        return alumno;
    }

    @DeleteMapping("/borrar/{id}")
    public Alumno deleteAlumno(@PathVariable int id)
    {
        Alumno busquedaAlumno = this.devolverAlumnoPorId(id);
        if (busquedaAlumno != null) this.listaAlumnos.remove(busquedaAlumno);
        return busquedaAlumno;
    }

    @PutMapping
    public Alumno putAlumno (@RequestBody  Alumno alumno)
    {
        Alumno busquedaAlumno = this.devolverAlumnoPorId(alumno.getId());
        if (busquedaAlumno != null)
        {
            busquedaAlumno.setEmail(alumno.getEmail());
            busquedaAlumno.setNombre(alumno.getNombre());
            busquedaAlumno.setEdad(alumno.getEdad());
            busquedaAlumno.setCurso(alumno.getCurso());
        }
        return busquedaAlumno;
    }

    @PatchMapping
    public Alumno patchAlumno (@RequestBody Alumno alumno)
    {
        Alumno busquedaAlumno = this.devolverAlumnoPorId(alumno.getId());
        if (busquedaAlumno != null)
        {
            if (alumno.getCurso() != null)
                busquedaAlumno.setCurso(alumno.getCurso());
            if (alumno.getNombre() != null)
                busquedaAlumno.setNombre(alumno.getNombre());
            if (alumno.getEmail() != null)
                busquedaAlumno.setEmail(alumno.getEmail());
            if (alumno.getEdad()  > 0)
                busquedaAlumno.setEdad(alumno.getEdad());
        }
        return busquedaAlumno;
    }


    // Lo hago metodo privado para reutilizar codigo.
    private Alumno devolverAlumnoPorId(int idParametro)
    {
        return this.listaAlumnos.stream()
                .filter(alumno -> alumno.getId() == idParametro)
                .findFirst().orElse(null);
    }

}
