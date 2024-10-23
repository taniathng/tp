package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.Time;
import seedu.address.model.doctor.Doctor;
import seedu.address.model.patient.Patient;
import seedu.address.model.shared.Date;
import seedu.address.testutil.DoctorBuilder;
import seedu.address.testutil.PatientBuilder;

public class DeleteAppointmentCommandTest {

    private Model model;
    private Appointment appointmentToDelete;
    private Doctor doctor;
    private Patient patient;

    @BeforeEach
    public void setUp() {
        model = new ModelManager();
        doctor = new DoctorBuilder().build();
        patient = new PatientBuilder().build();

        appointmentToDelete = new Appointment(doctor, patient, new Date("23-04-2023"), new Time("1100"));
    }

    @Test
    public void execute_validAppointment_success() throws Exception {
        DeleteAppointmentCommand deleteAppointmentCommand = new DeleteAppointmentCommand(appointmentToDelete);

        CommandResult result = deleteAppointmentCommand.execute(model);

        // Checking the success message after the deletion
        assertEquals(String.format(DeleteAppointmentCommand.MESSAGE_DELETE_APPOINTMENT_SUCCESS,
                appointmentToDelete.getPatient().getName(),
                appointmentToDelete.getDoctor().getName(),
                appointmentToDelete.getDate(),
                appointmentToDelete.getTime()), result.getFeedbackToUser());
    }

    @Test
    public void execute_invalidAppointment_throwsCommandException() {
        Appointment invalidAppointment = new Appointment(new DoctorBuilder().build(),
                new PatientBuilder().build(), new Date("12-12-2023"), new Time("0900"));

        DeleteAppointmentCommand deleteAppointmentCommand = new DeleteAppointmentCommand(invalidAppointment);

        assertThrows(CommandException.class, () -> deleteAppointmentCommand.execute(model),
                DeleteAppointmentCommand.MESSAGE_INVALID_APPOINTMENT_ID);
    }

    @Test
    public void equals() {
        Appointment otherAppointment = new Appointment(new DoctorBuilder().build(),
                new PatientBuilder().build(), new Date("23-05-2023"), new Time("1200"));

        DeleteAppointmentCommand deleteFirstAppointmentCommand = new DeleteAppointmentCommand(appointmentToDelete);
        DeleteAppointmentCommand deleteSecondAppointmentCommand = new DeleteAppointmentCommand(otherAppointment);

        // same object -> returns true
        assertEquals(deleteFirstAppointmentCommand, deleteFirstAppointmentCommand);

        // same appointment -> returns true
        DeleteAppointmentCommand deleteFirstAppointmentCommandCopy = new DeleteAppointmentCommand(appointmentToDelete);
        assertEquals(deleteFirstAppointmentCommand, deleteFirstAppointmentCommandCopy);

        // different appointment -> returns false
        assertEquals(false, deleteFirstAppointmentCommand.equals(deleteSecondAppointmentCommand));

        // different type -> returns false
        assertEquals(false, deleteFirstAppointmentCommand.equals(1));
    }
}
