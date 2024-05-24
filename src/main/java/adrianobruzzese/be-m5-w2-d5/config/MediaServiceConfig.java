package adrianobruzzese.be-m5-w2-d5.config;

// Importazione delle classi necessarie per la configurazione
import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class MediaServiceConfig {

    // Il metodo cloudinaryConfig crea un bean per gestire l'upload di immagini
    @Bean
    public Cloudinary cloudinaryConfig(
            @Value("${cloudinary.name}") String cloudName,  // Inietta il nome del cloud da application.properties
            @Value("${cloudinary.key}") String apiKey,      // Inietta l'API key da application.properties
            @Value("${cloudinary.secret}") String apiSecret // Inietta l'API secret da application.properties
    ) {
        // Creazione di una mappa per configurare i parametri di Cloudinary
        Map<String, String> configValues = new HashMap<>();
        configValues.put("cloud_name", cloudName);
        configValues.put("api_key", apiKey);
        configValues.put("api_secret", apiSecret);

        // Creazione di un nuovo oggetto Cloudinary con la configurazione specificata
        return new Cloudinary(configValues);
    }
}
