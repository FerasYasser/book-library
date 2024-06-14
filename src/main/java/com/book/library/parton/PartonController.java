package com.book.library.parton;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/partons")
@AllArgsConstructor
public class PartonController {
    private final PartonService partonService;

    @GetMapping
    public ResponseEntity<Page<Parton>> getAll(@RequestParam(defaultValue = "0") Integer page,
                                             @RequestParam(defaultValue = "10") Integer size){
        return partonService.getAll(page,size);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Parton> getById(@PathVariable Long id)
    {
        return partonService.getById(id) ;
    }
    @PostMapping
    public ResponseEntity<Parton> save(@RequestBody Parton parton)
    {
        return partonService.save(parton);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Parton> update(@PathVariable Long id , @RequestBody Parton parton)
    {
        return partonService.update(id,parton);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id)
    {
        return partonService.delete(id);
    }

}
