public class testSubjectForRASHIDhospital {
    public static void main(String[] args) {
        HospitalER ICU = new HospitalER();

        // Admit patients
        ICU.PatientToAdmit("Harun", false);
        ICU.PatientToAdmit("Hassan", true);
        ICU.PatientToAdmit("Peter", false);
        ICU.PatientToAdmit("Parker", true);

        // Show queue and stack
        ICU.showStatus();

        // Treat patients
        ICU.treatPatient();
        ICU.treatPatient();
        ICU.showStatus();

        // UndoING Thetreatment
        ICU.undoTheTreatment();
        ICU.showStatus();

        // Treat patients
        ICU.treatPatient();
        ICU.treatPatient();
        ICU.treatPatient();
        ICU.showStatus();
    }
}