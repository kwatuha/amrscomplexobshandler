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
import org.openmrs.api.APIException;
import org.apache.commons.collections.ListUtils;
import org.openmrs.Concept;
import org.openmrs.Field;
import org.openmrs.FieldType;
import org.openmrs.Form;
import org.openmrs.FormField;
import org.openmrs.FormResource;
import org.openmrs.Obs;
import org.openmrs.api.context.Context;
import org.openmrs.api.FormService;
import org.openmrs.obs.SerializableComplexObsHandler;

public class AMRSComplexObsHandler implements SerializableComplexObsHandler {



    public Set<FormField> getFormFields() {
            Set<FormField> formFields = new HashSet<FormField>();
            Field firstName = new Field();
                      Concept firstNameConcept = new Concept();
                      FieldType firstNameFieldType = new FieldType();
                      firstNameConcept.setId(5574);
                      firstNameFieldType.setId(1);
                      firstName.setConcept(firstNameConcept);
                      firstName.setFieldType(firstNameFieldType);
                      firstName.setName("FirstName");



       /* Field lastName = new Field();
        FieldType lastNameFieldType = new FieldType();
        Concept lastNameConcept = new Concept();
                  lastNameConcept.setId(1728);
                  lastNameFieldType.setId(1);
                  lastName.setConcept(lastNameConcept);
                  lastName.setFieldType(lastNameFieldType);
                  lastName.setName("NumberOfChildren");*/



            FormField firstNameFormField = new FormField();
            firstNameFormField.setField(firstName);
         /*   FormField lastNameFormField = new FormField();
            lastNameFormField.setField(lastName);*/






            formFields.add(firstNameFormField);
            /*formFields.add(lastNameFormField);*/


            return formFields;
        }

        public Obs saveObs(Obs obs) throws APIException {

            System.out.print(" \n\n\n\n here comes the complex data========================================\n\n\n=====" );


            System.out.print(obs.getValueComplex()+" was complex data========================================" );

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
