package com.team11.hhs.dbseeds;

import com.team11.hhs.model.MedicalProcedure;
import com.team11.hhs.repository.MedicalProcedureRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class MedicalProcedureSeeder implements CommandLineRunner {

    @Autowired
    private MedicalProcedureRepo medicalProcedureRepo;

    @Override
    public void run(String... args) throws Exception {
        seedDB();
    }

    private void seedDB() {
        ArrayList<MedicalProcedure> procedures = new ArrayList<MedicalProcedure>();
        MedicalProcedure p1 = new MedicalProcedure(1, "surgery", 10000);
        MedicalProcedure p2 = new MedicalProcedure(2, "broken bone", 5000);
        MedicalProcedure p3 = new MedicalProcedure(3, "administer medication", 500);
        procedures.add(p1);
        procedures.add(p2);
        procedures.add(p3);
        for (MedicalProcedure procedure : procedures) {
            if (medicalProcedureRepo.findByProcedureName(procedure.getProcedureName()) == null) {
                medicalProcedureRepo.save(procedure);
            }
        }
    }

}
