import { Navigation } from "@/ui/Navigation";
import { ReactNode } from "react";

export default function sectionsLayout({ children }: Readonly<{ children: ReactNode }>) {
    return (
        <>
            <Navigation />
            {children}
        </>
    )
}