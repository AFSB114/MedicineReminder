"use client"

import { useParams,  } from "next/navigation";
import { useCallback, useEffect } from "react";
import { Navigation } from "@/ui/Navigation";

export default function SuspendPage() {
    const params = useParams();
    const id = parseInt(params.id as string);

    const apiUrl = `${process.env.NEXT_PUBLIC_API_URL}/prescription`

    const suspeded = useCallback(async () => {
        try {
            const res = await fetch(`${apiUrl}/suspended/${id}`, {
                method: "PUT"
            })
            const resJson = await res.json()
            if (!resJson.ok) throw new Error("No se pudo suspender la receta")
        } catch (e) {
            console.error(e)
        }
    }, [apiUrl])

    useEffect(() => {
        suspeded()
    }, [suspeded])
    
    return <Navigation />
}