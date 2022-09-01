package com.icecream.product.migrate;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.FlywayException;
import org.flywaydb.core.api.MigrationInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootApplication(exclude={HibernateJpaAutoConfiguration.class})
@ComponentScan(basePackages = {"com.icecream.migrate"},
	excludeFilters=@ComponentScan.Filter(SpringBootApplication.class))
public class ProductServiceMigrate implements ApplicationRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceMigrate.class);
	private final DataSource dataSource;

	@Autowired
	public ProductServiceMigrate(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Bean
	Flyway flyway (){
		return Flyway.configure().dataSource(this.dataSource).load();
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("============================================================================================================================================================");
		System.out.println("============================================================================================================================================================");
		System.out.println("============================================================================================================================================================");
		System.out.println("============================================================================================================================================================");
		LOGGER.info(String.join("-", args.getOptionNames()));
		List<String> optionValues = args.getOptionValues("action");
		if (optionValues == null || optionValues.size() != 1) {
			return;
		}
		String action = optionValues.get(0).toLowerCase();
		Flyway flyway = flyway();
		switch (action) {
			case "migrate":
				try {
					flyway.migrate();
					LOGGER.info("migrate success");
				}
				catch (FlywayException ex) {
					LOGGER.error("migrate", ex);
				}
				break;
			case "info":
				MigrationInfoService info = flyway.info();
				Arrays.stream(info.all()).forEach(a -> {
					LOGGER.info("Version: " + a.getVersion());
					LOGGER.info("\tDescription: " + a.getDescription());
					LOGGER.info("\tScript: " + a.getScript());
					LOGGER.info("\tState: " + a.getState());
				});
				break;
			case "validate":
				try {
					flyway.validate();
					LOGGER.info("migration validate success");
				}
				catch (FlywayException ex) {
					LOGGER.error("validate", ex);
				}
				break;
			default:
				LOGGER.error("action " + action + " do not support");
				break;
		}
	}

	public static void main (String[] args) {
		new SpringApplicationBuilder(ProductServiceMigrate.class).web(WebApplicationType.NONE).run(args);
	}
}
