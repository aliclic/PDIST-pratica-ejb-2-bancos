package com.gugawag.pdist.ejbs;

import com.gugawag.pdist.model.Mensagem;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.Arrays;
import java.util.List;

@Stateless(name = "mensagemService")
@Remote
public class MensagemService {

    @EJB
    private MensagemDAO mensagemDao;

    private final List<String> palavrasProibidas = Arrays.asList("palavrão1", "palavrão2");

    public void inserir(long id, String texto) {
        for (String palavrão : palavrasProibidas) {
            if (texto.toLowerCase().contains(palavrão)) {
                throw new IllegalArgumentException("Mensagem contém palavrões e não pode ser inserida.");
            }
        }
        Mensagem novaMensagem = new Mensagem(id, texto);
        mensagemDao.inserir(novaMensagem);
    }

    public List<Mensagem> listar() {
        return mensagemDao.listar();
    }
}