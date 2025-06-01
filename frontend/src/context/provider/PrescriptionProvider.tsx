"use client"

import { ReactNode, useCallback, useEffect, useState } from "react";
import { PrescriptionContext } from "../PrescriptionContext";
import { PrescriptionType, ListTimes } from "@/types";

export default function PrescriptionProvider({children}:{children: ReactNode}) {
    const [prescriptionList, setPrescriptionList] = useState<PrescriptionType[] | null>(null)
    const apiUrl = `${process.env.NEXT_PUBLIC_API_URL}/prescription`
    
    const getPrescriptions = useCallback(async () => {
        try {
            const res = await fetch(`${apiUrl}/`)
            if (!res.ok) throw new Error("Error al traer las recetas")
            const list: PrescriptionType[] = await res.json()
            setPrescriptionList(list)
        } catch (e) {
            console.error(e)
        }
    }, [apiUrl])

    useEffect(() => {
        getPrescriptions()
    }, [getPrescriptions])

    const addPrescription = useCallback(async (prescription: Omit<PrescriptionType, "id" | "suspended" | "suspendedDate" | "scheduleList"> & ListTimes) => {
        try {
            const res = await fetch(`${apiUrl}/`, {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(prescription)
            })
            const resJson = await res.json()
            if (!resJson.ok) throw new Error("No se pudo agregar la receta")
            getPrescriptions()
        } catch (e) {
            console.error(e)
        }
    }, [apiUrl])

    return (
        <PrescriptionContext.Provider
            value={{
                prescriptionList,
                addPrescription
            }}
        >
            {children}
        </PrescriptionContext.Provider>
    )
}