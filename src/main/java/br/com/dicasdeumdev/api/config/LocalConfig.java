package br.com.dicasdeumdev.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.dicasdeumdev.api.domain.User;
import br.com.dicasdeumdev.api.repositories.UserRepository;

@Configuration
@Profile("local")
public class LocalConfig implements CommandLineRunner {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private Environment env; // Para verificar os perfis ativos
    
    @Override
    public void run(String... args) throws Exception {
        // Verifica quais perfis estão ativos
        String[] activeProfiles = env.getActiveProfiles();
        System.out.println("=== PERFIS ATIVOS: " + String.join(", ", activeProfiles) + " ===");
        
        if (env.acceptsProfiles(org.springframework.core.env.Profiles.of("local"))) {
            System.out.println("=== PERFIL LOCAL DETECTADO - INICIANDO CARGA DE DADOS ===");
            startDB();
        } else {
            System.out.println("=== PERFIL LOCAL NÃO ESTÁ ATIVO ===");
        }
    }
    
    public void startDB() {
        System.out.println("Populando banco de dados com usuários...");
        
        try {
            User u1 = new User(null, "Maria", "maria@gmail.com", "123");
            User u2 = new User(null, "João", "joao@gmail.com", "456");
            
            // Salva individualmente para melhor debug
            User saved1 = userRepository.save(u1);
            User saved2 = userRepository.save(u2);
            
            System.out.println("Usuário 1 salvo com ID: " + saved1.getId());
            System.out.println("Usuário 2 salvo com ID: " + saved2.getId());
            
        } catch (Exception e) {
            System.out.println("ERRO ao salvar usuários: " + e.getMessage());
            e.printStackTrace();
        }
    }
}