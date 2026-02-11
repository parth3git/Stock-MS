package com.example.Stock.Model;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

@Service
public class csvService {
        public File generateStockCSV(List<Stock> stocks) throws Exception {

                String path = "E:/reports/";
                new File(path).mkdirs();

                File file = new File(path + "stock-report.csv");
                FileWriter writer = new FileWriter(file);

                // Header
                writer.append("StockId,Product,Quantity,Threshold\n");

                for (Stock s : stocks) {
                        writer.append(s.getsId() + ",")
                                .append(s.getProduct().getpName() + ",")
                                .append(String.valueOf(s.getInStock()) + ",")
                                .append(String.valueOf(s.getThreshold()) + "\n");
                }

                writer.flush();
                writer.close();

                return file;
        }
}
