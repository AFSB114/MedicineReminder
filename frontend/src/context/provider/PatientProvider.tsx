"use client"

import { ReactNode, useCallback, useEffect, useState } from "react";
import { PatientContext } from "../PatientContext";
import { PatientType, Response } from "@/types";

export default function PatientProvider({ children }: { children: ReactNode }) {
    const [patientList, setPatientList] = useState<PatientType[] | null>(null)
    const apiUrl = `${process.env.NEXT_PUBLIC_API_URL}/patient`

    const getPatients = useCallback(async () => {
        try {
            const res = await fetch(`${apiUrl}/`)
            if (!res.ok) throw new Error("Error al traer a los pacientes")
            const list: PatientType[] = await res.json()
            setPatientList(list)
        } catch (e) {
            console.error(e)
        }
    }, [apiUrl]) 
    
    useEffect(() => {
        getPatients()
    }, [getPatients])
    
    const addPatient = useCallback(async (patient: Omit<PatientType, "id">) => {
        try {
            const res = await fetch(`${apiUrl}/`, {
                method: "POST",
                headers: {"Content-Type": "application/json"},
                body: JSON.stringify(patient)
            })
            const resJson: Response<PatientType> = await res.json()
            if (!resJson.ok) throw new Error("Error al agregar un paciente")
            getPatients()
        } catch (e) {
            console.error(e)
        }
    },[apiUrl])

    return (
        <PatientContext.Provider
            value={{
                patientList,
                addPatient
            }}
        >
            {children}
        </PatientContext.Provider>
    )
}