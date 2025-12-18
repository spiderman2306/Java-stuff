public class HospitalER {
    private QueueAsMyLinkedList<String> patient;
    private StackAsMyLinkedList<String> treatment;

    public HospitalER() {
        patient = new QueueAsMyLinkedList<>();
        treatment = new StackAsMyLinkedList<>();
    }


    public void PatientToAdmit(String name, boolean emergency) {
        if (emergency) {
            patient.enqueueForEmergency(name);
            System.out.println("The Emergency patient admitted is: " + name);
        } else {
            patient.enqueue(name);
            System.out.println("Your  admitted Patient is: " + name);
        }
    }

    // Treating the next patient
    public void treatPatient() {
        if (patient.isEmpty()) {
            System.out.println("No patients to treat, wait for people to get sick(Allah forbid they do).");
            return;
        }
        String ToPatientQueue = patient.dequeue();
        treatment.push(ToPatientQueue);
        System.out.println("Treated patient: " + ToPatientQueue);
    }

    // control Z last treatment
    public void undoTheTreatment() {
        if (treatment.isEmpty()) {
            System.out.println("there aren't any treatments to reverse.");
            return;
        }
        String undo = treatment.pop();
        patient.enqueueForEmergency(undo);
        System.out.println("Undid treatment for patient: " + undo);
    }

    // Showing the status
    public void showStatus() {
        System.out.println("Current Queue: " + patient);
        System.out.println("Treatment History (Stack): " + treatment);
    }
}