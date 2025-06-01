import { PatientContext } from "@/context/PatientContext";
import { PatientContextType } from "@/types";
import { useContext } from "react";

export default function usePatient(): PatientContextType {
    const context = useContext(PatientContext)
    if (!context) throw new Error("usePatient debe ser usado con PatientProvider")
    return context
}