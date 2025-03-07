package com.mycompany.sistema_bancario;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

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
 * @author Ian Nakamura Okano Preste
 * @matricula 202335038
 */

public class Main { // main meremente para testes
        public static void main(String[] args) throws IOException {
                String filePathUsuarios = "src/file/java/com/mycompany/sistema_bancario/usuarios.json";
                String filePathInvestimentos = "src/file/java/com/mycompany/sistema_bancario/investimentos.json";

                UsuarioService usuarioService = new UsuarioService(filePathUsuarios, filePathInvestimentos);

                // Exemplo de registro de investimento de renda fixa
                Map<String, Object> investimentoRendaFixa = new HashMap<>();
                investimentoRendaFixa.put("produto", "Tesouro Selic 2025");
                investimentoRendaFixa.put("valorInvestido", 1000.0);
                investimentoRendaFixa.put("prazoMinimo", "2025-03-01");
                investimentoRendaFixa.put("vencimento", "2020-03-01");
                usuarioService.registrarInvestimentoRendaFixa("0002", investimentoRendaFixa);

                // Exemplo de registro de investimento de renda variável
                Map<String, Object> investimentoRendaVariavel = new HashMap<>();
                investimentoRendaVariavel.put("produto", "Ação 1");
                investimentoRendaVariavel.put("risco", 25.0);
                investimentoRendaVariavel.put("rentabilidade", 40.0);
                usuarioService.registrarInvestimentoRendaVariavel("0002", investimentoRendaVariavel);
        }
}
