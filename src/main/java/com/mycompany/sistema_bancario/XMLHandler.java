package com.mycompany.sistema_bancario;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.util.List;

import com.mycompany.sistema_bancario.ListaUsuarios;

/**
 *
 * @author Darlan Henrique da Costa Silva
 * @matricula 202176038
 */
/**
 *
 * @author Rômulo Ferreira do Amaral
 * @matricula 202335015
 */
/**
 *
 * @author  Ian Nakamura Okano Preste
 * @matricula 202335038
 */


public class XMLHandler {

    // Método para salvar os dados dos usuários em um arquivo XML
    public static void salvarEmXml(List<Usuario> usuarios, String caminhoArquivo) throws FileNotFoundException {
        try {
            JAXBContext context = JAXBContext.newInstance(ListaUsuarios.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            ListaUsuarios listaUsuarios = new ListaUsuarios();
            listaUsuarios.setUsuarios(usuarios);
            for (Usuario usuario : usuarios) {
                if (usuario instanceof Cliente) {
                    Cliente cliente = (Cliente) usuario;
                    cliente.setSaldo(cliente.getSaldo()); // Garante que o saldo está atualizado
                }}

            marshaller.marshal(listaUsuarios, new FileOutputStream(caminhoArquivo));
            System.out.println("Usuários salvos com sucesso em " + caminhoArquivo);
        } catch (JAXBException e) {
            System.err.println("Erro ao salvar usuários: " + e.getMessage());
        }
    }

    public static List<Usuario> carregarDeXml(String caminhoArquivo) throws FileNotFoundException {
        try {
            JAXBContext context = JAXBContext.newInstance(ListaUsuarios.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            ListaUsuarios listaUsuarios = (ListaUsuarios) unmarshaller.unmarshal(new File(caminhoArquivo));
            return listaUsuarios.getUsuarios();
        } catch (JAXBException e) {
            System.err.println("Erro ao carregar usuários: " + e.getMessage());
            return null;
        }
    }

    

}