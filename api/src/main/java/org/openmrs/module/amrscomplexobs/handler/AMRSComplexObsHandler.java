package org.openmrs.module.amrscomplexobs.handler;

/**
 * Created with IntelliJ IDEA.
 * User: alfayo
 * Date: 3/26/13
 * Time: 12:48 PM
 * To change this template use File | Settings | File Templates.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import org.openmrs.util.OpenmrsConstants;
import org.openmrs.util.OpenmrsUtil;
import org.openmrs.util.OpenmrsConstants.PERSON_TYPE;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.CharArrayReader;
import java.io.File;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


import org.openmrs.api.APIException;
import org.apache.commons.collections.ListUtils;
import org.openmrs.Concept;
import org.openmrs.Field;
import org.openmrs.FieldType;
import org.openmrs.Form;
import org.openmrs.FormField;
import org.openmrs.FormResource;
import org.openmrs.Obs;

import org.openmrs.ConceptName;
import org.openmrs.Encounter;
import org.openmrs.Location;
import org.openmrs.Obs;
import org.openmrs.Order;
import org.openmrs.Patient;
import org.openmrs.Person;
import org.openmrs.api.context.Context;
import org.openmrs.api.impl.ObsServiceImpl;

import org.openmrs.api.context.Context;
import org.openmrs.obs.ComplexData;
import org.openmrs.obs.ComplexObsHandler;
import org.openmrs.api.FormService;
import org.openmrs.api.ObsService;
import org.openmrs.api.ConceptService;
import org.openmrs.api.AdministrationService;
import org.openmrs.obs.SerializableComplexObsHandler;

public class AMRSComplexObsHandler implements SerializableComplexObsHandler {



    public Set<FormField> getFormFields() {
        Set<FormField> formFields = new HashSet<FormField>();
        Field patientAge = new Field();
        Concept patientAgeConcept = new Concept();
        FieldType patientAgeFieldType = new FieldType();
        patientAgeConcept.setId(5574);
        patientAgeFieldType.setId(1);
        patientAge.setConcept(patientAgeConcept);
        patientAge.setFieldType(patientAgeFieldType);
        patientAge.setName("patientAge");
        //Define parent/guardian fields
        Field pgFamilyName = new Field();
        FieldType pgFamilyNameFieldType = new FieldType();
        pgFamilyNameFieldType.setId(2);

        pgFamilyName.setTableName("patient_name");
        pgFamilyName.setAttributeName("family_name");
        pgFamilyName.setFieldType(pgFamilyNameFieldType);
        pgFamilyName.setName("Guardian or Parent Family Name");
        pgFamilyName.setDescription("The name of Guardian or Parent Family Name");

        //Neighbour
        Field neighbourFamilyName = new Field();
        FieldType neighbourFamilyNameFieldType = new FieldType();
        neighbourFamilyNameFieldType.setId(2);

        neighbourFamilyName.setTableName("patient_name");
        neighbourFamilyName.setAttributeName("family_name");
        neighbourFamilyName.setFieldType(neighbourFamilyNameFieldType);
        neighbourFamilyName.setName("Neighbour Family Name");
        neighbourFamilyName.setDescription("The name of Neigbour Family Name");

       //add section

        Field treatmentSupporterDetails = new Field();
        FieldType treatmentSupporterDetailsFieldType = new FieldType();
        treatmentSupporterDetailsFieldType.setId(5);
        treatmentSupporterDetails.setFieldType(treatmentSupporterDetailsFieldType);
        treatmentSupporterDetails.setName("Treatment Supporter Details");
        treatmentSupporterDetails.setDescription("treatment Supporter Details");

        FormField treatmentSupporterDetailsFormField = new FormField();
        treatmentSupporterDetailsFormField.setField(treatmentSupporterDetails);



        //end of section

        //create form fields

        FormField patientAgeFormField = new FormField();
        patientAgeFormField.setField(firstName);


        FormField pgFamilyNameFormField = new FormField();
        pgFamilyNameFormField.setField(pgFamilyName);

        FormField neighbourFamilyNameFormField = new FormField();
        neighbourFamilyNameFormField.setField(neighbourFamilyName);


        formFields.add(patientAgeFormField);
        formFields.add(pgFamilyNameFormField);
        formFields.add(treatmentSupporterDetailsFormField);
        formFields.add(neighbourFamilyNameFormField);


        return formFields;
    }

    public Obs saveObs(Obs obs) throws APIException {


        ObsService os = Context.getObsService();
        ConceptService cs = Context.getConceptService();
        AdministrationService as = Context.getAdministrationService();

        // make sure the file isn't there to begin with
        File complexObsDir = OpenmrsUtil.getDirectoryInApplicationDataDirectory(as
                .getGlobalProperty(OpenmrsConstants.GLOBAL_PROPERTY_COMPLEX_OBS_DIR));
        File createdFile = new File(complexObsDir, "alphaComplexObsfile.txt");
        if (createdFile.exists())
            createdFile.delete();

        // the complex data to put onto an obs that will be saved
        Reader input = new CharArrayReader("This is a string to save to a file".toCharArray());
        ComplexData complexData = new ComplexData("alphaComplexObsfile.txt", input);

        // must fetch the concept instead of just new Concept(8473) because the attributes on concept are checked
        // this is a concept mapped to the text handler
        Concept questionConcept = cs.getConcept(6106);

        Obs obsToSave = new Obs(new Person(obs.getPersonId()), questionConcept, new Date(), new Location(1));
        obsToSave.setComplexData(complexData);

        try {
            os.saveObs(obsToSave, null);
        }
        finally {
            // we always have to delete this inside the same unit test because it is outside the
            // database and hence can't be "rolled back" like everything else
            createdFile.delete();
        }

        return null;
    }

    public Obs getObs(Obs obs, String view) {
        return null;
    }

    public boolean purgeComplexData(Obs obs) {
        return false;
    }

    public String serializeFormData(String data) {
        return null;
    }


}
