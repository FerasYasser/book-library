package com.book.library.parton;


import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class PartonService {
    private final PartonRepository partonRepository;
    
    
    public ResponseEntity<Page<Parton>> getAll(Integer page, Integer size){
        PageRequest pageRequest = PageRequest.of(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(partonRepository.findAll(pageRequest));
    }


    public ResponseEntity<Parton> getById(Long id)
    {
        Parton parton = partonRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Parton not found with id " + id));
        return ResponseEntity.status(HttpStatus.OK).body(parton);
    }


    public ResponseEntity<Parton> save(Parton parton)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(partonRepository.save(parton));
    }


    public ResponseEntity<Parton> update(Long id , Parton parton)
    {
        ResponseEntity<Parton> newParton = getById(id);
        parton.setId(newParton.getBody().getId());
        return ResponseEntity.status(HttpStatus.OK).body(partonRepository.save(parton));
    }

    public ResponseEntity<Void> delete(Long id)
    {
        partonRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
