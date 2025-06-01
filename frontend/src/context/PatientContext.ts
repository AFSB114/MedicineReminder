"use client"

import { PatientContextType } from "@/types";
import { createContext } from "react";

export const PatientContext = createContext<PatientContextType | undefined>(undefined);