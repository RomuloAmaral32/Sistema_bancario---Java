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


public class XMLHandler {

    // Método para salvar os dados dos usuários em um arquivo XML
    public static void salvarEmXml(List<Usuario> usuarios, String caminhoArquivo) throws FileNotFoundException {
        try {
            // Cria um contexto JAXB para a classe ListaUsuarios
            JAXBContext context = JAXBContext.newInstance(ListaUsuarios.class); // Use ListaUsuarios em vez de Usuario

            // Cria um Marshalling (converter objetos para XML)
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);  // Formatar o XML para leitura

            // Cria o objeto ListaUsuarios
            ListaUsuarios listaUsuarios = new ListaUsuarios(usuarios);

            // Converte e escreve os dados no arquivo XML
            marshaller.marshal(listaUsuarios, new FileOutputStream(caminhoArquivo));
            System.out.println("Usuários salvos com sucesso em " + caminhoArquivo);
        } catch (JAXBException e) {
            System.err.println("Erro ao salvar usuários: " + e.getMessage());
        }
    }

    // Método para carregar os dados dos usuários a partir de um arquivo XML
    public static List<Usuario> carregarDeXml(String caminhoArquivo) {
        try {
            // Cria um contexto JAXB para a classe ListaUsuarios
            JAXBContext context = JAXBContext.newInstance(ListaUsuarios.class);

            // Cria um Unmarshaller (converter XML de volta para objetos)
            Unmarshaller unmarshaller = context.createUnmarshaller();

            // Lê o arquivo XML e retorna a lista de usuários
            ListaUsuarios listaUsuarios = (ListaUsuarios) unmarshaller.unmarshal(new File(caminhoArquivo));

            // Retorna a lista de usuários
            return listaUsuarios.getUsuarios();
        } catch (JAXBException e) {
            System.err.println("Erro ao carregar usuários: " + e.getMessage());
            return null;
        }
    }

    

}