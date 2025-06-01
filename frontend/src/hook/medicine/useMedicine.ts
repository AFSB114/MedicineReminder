import { MedicineContext } from "@/context/MedicineContext";
import { MedicineContextType } from "@/types";
import { useContext } from "react";

export default function useMedicine(): MedicineContextType {
    const context = useContext(MedicineContext)
    if (!context) throw new Error("useMedicine debe ser usado con MedicineProvider")
    return context
}