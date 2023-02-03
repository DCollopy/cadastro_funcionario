package br.com.cadastro.persistencia.configuracao;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class DataSource {

    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("pensealem");
        dataSource.setUrl(
                "jdbc:mysql://localhost:3306/myDb?createDatabaseIfNotExist=true");

        return dataSource;
    }
}