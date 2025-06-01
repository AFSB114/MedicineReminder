import { PrescriptionContext } from "@/context/PrescriptionContext";
import { PrescriptionContextType } from "@/types";
import { useContext } from "react";

export default function usePrescription(): PrescriptionContextType {
    const context = useContext(PrescriptionContext)
    if (!context) throw new Error("usePrescription debe ser usado con PrescriptionProvider")
    return context
}