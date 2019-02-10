/**
 * Copyright (c) 2017 Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software except in
 * compliance with the terms of the License at:
 * http://java.net/projects/javaeetutorial/pages/BerkeleyLicense
 */
package javaeetutorial.web.dukeetf;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;

/* Updates price and volume information every second */
@Startup
@Singleton
public class PriceVolumeBean {

    /* Use the container's timer service */
    @Resource
    TimerService tservice;
    private DukeETFServlet servlet;
    private volatile int count = 0;
    private static final Logger logger = Logger.getLogger("PriceVolumeBean");
    private List<String> linesOfPrices;
//    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy");
//    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    @PostConstruct
    public void init() {
        /* Intialize the EJB and create a timer */
        logger.log(Level.INFO, "Initializing EJB.");
        servlet = null;
        tservice.createIntervalTimer(1000, 1000, new TimerConfig());

        linesOfPrices = readFileLines("project4input.txt");
    }

    public void registerServlet(DukeETFServlet servlet) {
        /* Associate a servlet to send updates to */
        this.servlet = servlet;
    }

    @Timeout
    public void timeout() {
        /* Adjust price and volume and send updates */

        if (linesOfPrices.size() > count) {

            String line = linesOfPrices.get(count++);
//            String[] splits = line.split(",");
//            LocalDate date = LocalDate.parse(splits[0].trim(), DATE_FORMATTER);
//            LocalTime time = LocalTime.parse(splits[1].trim(), TIME_FORMATTER);
//            double price = Double.valueOf(splits[2].trim());
//            int volume = Integer.valueOf(splits[3].trim());
//            double highest52 = Double.valueOf(splits[4].trim());
//            double lowest52 = Double.valueOf(splits[5].trim());
//
//            ETF etf = new ETF(price, volume, date, time, highest52, lowest52);
            servlet.send(line);
        }
    }

    private List<String> readFileLines(String fileName) {

        List<String> lines = new ArrayList<>();

        //Get file from resources folder
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());

        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lines.add(line);
            }

            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }
}
