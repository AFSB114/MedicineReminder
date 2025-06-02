export interface PatientType {
    id: number,
    firstName: string,
    lastName: string,
    email: string
}

export interface PatientContextType {
    patientList: PatientType[] | null,
    addPatient: (patient: Omit<PatientType, "id">) => void
}

export interface MedicineType {
    id: number,
    name: string,
    description: string
}

export interface MedicineContextType {
    medicineList: MedicineType[] | null,
    addMedicine: (medicine: Omit<MedicineType, "id">) => void
}

export interface PrescriptionType {
    id: number,
    patient: { id: number } | PatientType<Partial>,
    medicine: { id: number } | MedicineType<Partial>,
    dosage: number | null,
    suspended: boolean,
    suspendedDate: string
    scheduleList: Omit<ScheduleType, "prescription">[]
}


export interface ScheduleType {
    id: number,
    prescription: PrescriptionType,
    time: string
}

export interface ListTimes {
    times: string[]
}

export interface PrescriptionContextType {
    prescriptionList: PrescriptionType[] | null,
    addPrescription: (prescription: Omit<PrescriptionType, "id" | "suspended" | "suspendedDate" | "scheduleList"> & ListTimes) => void
}

export interface Response<T> {
    status: string,
    message: string,
    ok: boolean,
    data: T[] | null,
    errors: string | null
}

export interface MedicineReminder {
    id: string
    firstName: string
    lastName: string
    email: string;
    scheduleTime: string;
    medicine: string;
    sentTime: string;
    secondSentTime: string;
    status: 'CONFIRMED' | 'NOT_CONFIRMED' | 'PENDING' | 'SENT';
}