import PatientProvider from "@/context/provider/PatientProvider";
import { ReactNode } from "react";

export default function patientLayout({ children }: { children: ReactNode }) {
    return (
        <PatientProvider>
            {children}
        </PatientProvider>
    )
}