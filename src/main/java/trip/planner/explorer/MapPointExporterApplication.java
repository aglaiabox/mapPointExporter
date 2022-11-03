package trip.planner.explorer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import trip.planner.explorer.controller.MapPointsController;

@SpringBootApplication
public class MapPointExporterApplication {

	public static void main(String[] args) {
		System.out.println("here");
		SpringApplication.run(MapPointExporterApplication.class, args);
	}

}
