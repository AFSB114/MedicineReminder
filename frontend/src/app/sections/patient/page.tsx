"use client"

import type React from "react"

import { useState } from "react"
import { User, Mail } from "lucide-react"
import usePatient from "@/hook/patient/usePatient"
import { PatientType } from "@/types"

export default function patientPage() {
    const { patientList, addPatient } = usePatient()
    const [formData, setFormData] = useState({
        firstName: "",
        lastName: "",
        email: "",
    })

    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault()

        if (formData.firstName && formData.lastName && formData.email) {
            const newPatient: Omit<PatientType, "id"> = {
                firstName: formData.firstName,
                lastName: formData.lastName,
                email: formData.email,
            }

            addPatient(newPatient)
            setFormData({ firstName: "", lastName: "", email: "" })
        }
    }

    const handleInputChange = (field: keyof typeof formData, value: string) => {
        setFormData((prev) => ({ ...prev, [field]: value }))
    }

    return (
        <div className="min-h-screen bg-zinc-800 p-6">
            <div className="mx-auto max-w-4xl space-y-8">
                {/* Formulario */}
                <div className="w-full rounded-2xl border border-zinc-800 bg-zinc-900 p-6">
                    <div className="text-2xl font-bold text-center text-white mb-4">
                        Agregar Paciente
                    </div>

                    <form onSubmit={handleSubmit} className="space-y-6">
                        <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                            <div className="space-y-2">
                                <label htmlFor="firstName" className="block font-medium text-zinc-200">
                                    Primer Nombre
                                </label>
                                <input
                                    id="firstName"
                                    type="text"
                                    placeholder="Ingresa tu primer nombre"
                                    value={formData.firstName}
                                    onChange={(e) => handleInputChange("firstName", e.target.value)}
                                    required
                                    maxLength={100}
                                    className="w-full px-4 py-2 border border-zinc-700 bg-zinc-800 text-white placeholder-zinc-500 rounded-md focus:outline-none focus:ring-2 focus:ring-zinc-600"
                                />
                            </div>

                            <div className="space-y-2">
                                <label htmlFor="lastName" className="block font-medium text-zinc-200">
                                    Apellido
                                </label>
                                <input
                                    id="lastName"
                                    type="text"
                                    placeholder="Ingresa tu apellido"
                                    value={formData.lastName}
                                    onChange={(e) => handleInputChange("lastName", e.target.value)}
                                    required
                                    maxLength={100}
                                    className="w-full px-4 py-2 border border-zinc-700 bg-zinc-800 text-white placeholder-zinc-500 rounded-md focus:outline-none focus:ring-2 focus:ring-zinc-600"
                                />
                            </div>
                        </div>

                        <div className="space-y-2">
                            <label htmlFor="email" className="block font-medium text-zinc-200">
                                Correo Electrónico
                            </label>
                            <input
                                id="email"
                                type="email"
                                placeholder="correo@ejemplo.com"
                                value={formData.email}
                                onChange={(e) => handleInputChange("email", e.target.value)}
                                required
                                maxLength={100}
                                className="w-full px-4 py-2 border border-zinc-700 bg-zinc-800 text-white placeholder-zinc-500 rounded-md focus:outline-none focus:ring-2 focus:ring-zinc-600"
                            />
                        </div>

                        <button
                            type="submit"
                            className="w-full bg-green-700 text-white py-2 rounded-md hover:bg-green-800 transition-colors"
                        >
                            Agregar Contacto
                        </button>
                    </form>
                </div>


                {/* Lista de contactos */}
                <div className="space-y-4 mt-8">
                    <h2 className="text-xl font-semibold text-white">Pacientes Agregados</h2>

                    {patientList === null ? (
                        <div className="rounded-2xl border border-zinc-800 bg-zinc-900">
                            <div className="flex items-center justify-center py-8">
                                <p className="text-zinc-400">No hay pacientes agregados aún</p>
                            </div>
                        </div>
                    ) : (
                        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
                            {patientList.map((contact) => (
                                <div
                                    key={contact.id}
                                    className="rounded-lg border border-zinc-800 bg-zinc-900 hover:shadow-md transition-shadow"
                                >
                                    <div className="p-6 space-y-3">
                                        <div className="flex items-center space-x-2">
                                            <User className="h-4 w-4 text-blue-500" />
                                            <span className="font-medium text-white">
                                                {contact.firstName} {contact.lastName}
                                            </span>
                                        </div>
                                        <div className="flex items-center space-x-2">
                                            <Mail className="h-4 w-4 text-green-500" />
                                            <span className="text-sm text-zinc-400">{contact.email}</span>
                                        </div>
                                    </div>
                                </div>
                            ))}
                        </div>
                    )}
                </div>
            </div>
        </div>
    )
}
