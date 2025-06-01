import MedicineProvider from "@/context/provider/MedicineProvider";
import PatientProvider from "@/context/provider/PatientProvider";
import PrescriptionProvider from "@/context/provider/PrescriptionProvider";
import { ReactNode } from "react";

export default function PrescriptionLayout({ children }: { children: ReactNode }) {
    return (
        <PrescriptionProvider>
            <PatientProvider>
                <MedicineProvider>
                    {children}
                </MedicineProvider>
            </PatientProvider>
        </PrescriptionProvider>
    )
}