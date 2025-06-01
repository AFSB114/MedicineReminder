"use client"

import { PrescriptionContextType } from "@/types"
import { createContext } from "react"

export const PrescriptionContext = createContext<PrescriptionContextType | undefined>(undefined)