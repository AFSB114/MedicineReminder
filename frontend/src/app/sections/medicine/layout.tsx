import { ReactNode } from "react";
import MedicineProvider from "@/context/provider/MedicineProvider"

export default function MedicineLayout({ children }: { children: ReactNode }) {
    return (
        <MedicineProvider>
            {children}
        </MedicineProvider>
    )
}