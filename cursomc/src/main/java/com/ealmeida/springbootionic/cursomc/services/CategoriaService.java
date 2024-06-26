package com.ealmeida.springbootionic.cursomc.services;

import com.ealmeida.springbootionic.cursomc.domain.Categoria;
import com.ealmeida.springbootionic.cursomc.domain.Cliente;
import com.ealmeida.springbootionic.cursomc.dto.CategoriaDTO;
import com.ealmeida.springbootionic.cursomc.repositories.CategoriaRepository;
import com.ealmeida.springbootionic.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public Categoria find(Integer id) {
        return categoriaRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("Objeto não encontrado! ID:" + id +
                        ", Tipo:" + Categoria.class.getName()));
    }

    public Categoria insert(Categoria newCategoria) {
        newCategoria.setId(null);
        return categoriaRepository.save(newCategoria);
    }

    public Categoria update(Categoria categoria) {
        Categoria newObj = find(categoria.getId());
        updateData(newObj, categoria);
        return categoriaRepository.save(newObj);
    }


    public void delete(Integer id) {
        find(id);
        try {
            categoriaRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new com.ealmeida.springbootionic.cursomc.services.exceptions.DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
        }
    }

    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return categoriaRepository.findAll(pageRequest);
    }

    public Categoria fromDTO(CategoriaDTO categoriaDTO) {
        return new Categoria(categoriaDTO.getId(), categoriaDTO.getNome());
    }


    private void updateData(Categoria newObj, Categoria categoria) {
        newObj.setNome(categoria.getNome());
    }
}
