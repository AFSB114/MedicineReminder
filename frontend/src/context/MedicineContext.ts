"use client"

import { MedicineContextType } from "@/types";
import { createContext } from "react";

export const MedicineContext = createContext<MedicineContextType | undefined>(undefined);