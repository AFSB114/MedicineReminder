"use client"

import { ReactNode, useCallback, useEffect, useState } from "react";
import { MedicineContext } from "../MedicineContext";
import { MedicineType, Response } from "@/types";

export default function MedicineProvider({ children }: { children: ReactNode }) {
    const [medicineList, setMedicineList] = useState<MedicineType[] | null>(null)
    const apiUrl = `${process.env.NEXT_PUBLIC_API_URL}/medicine`

    const getMedicines = useCallback(async () => {
        try {
            const res = await fetch(`${apiUrl}/`)
            if (!res.ok) throw new Error("Error al traer las medicinas")
            const list: MedicineType[] = await res.json()
            setMedicineList(list)
        } catch (e) {
            console.error(e)
        }
    }, [apiUrl])

    useEffect(() => {
        getMedicines()
    }, [getMedicines])


    const addMedicine = useCallback(async (medicine: Omit<MedicineType, "id">) => {
        try {
            const res = await fetch(`${apiUrl}/`, {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(medicine)
            })
            const resJson: Response<MedicineType> = await res.json()
            if (!resJson.ok) throw new Error("La medicina no se pudo agregar")
            getMedicines()
        } catch (e) {
            console.error(e)
        }
    }, [apiUrl])

    return (
        <MedicineContext.Provider
            value={{
                medicineList,
                addMedicine
            }}>
            {children}
        </MedicineContext.Provider>
    )
}