"use client"

import type React from "react"

import { useState } from "react"
import { Pill, FileText } from "lucide-react"
import useMedicine from "@/hook/medicine/useMedicine"
import { MedicineType } from "@/types"

export default function MedicinePage() {
    const {medicineList, addMedicine} = useMedicine()
    const [formData, setFormData] = useState({
        name: "",
        description: "",
    })

    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault()

        if (formData.name && formData.description) {
            const newMedicine: Omit<MedicineType, "id"> = {
                name: formData.name,
                description: formData.description,
            }

            addMedicine(newMedicine)
            setFormData({ name: "", description: "" })
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
                        Agregar Medicina
                    </div>

                    <form onSubmit={handleSubmit} className="space-y-4 mt-4">
                        <div className="space-y-2">
                            <label htmlFor="name" className="block font-medium text-zinc-200">Nombre</label>
                            <input
                                id="name"
                                type="text"
                                placeholder="Ej: Paracetamol, Ibuprofeno, etc."
                                value={formData.name}
                                onChange={(e) => handleInputChange("name", e.target.value)}
                                required
                                maxLength={100}
                                className="w-full px-4 py-2 border border-zinc-700 bg-zinc-800 text-white placeholder-zinc-500 rounded-md focus:outline-none focus:ring-2 focus:ring-zinc-600"
                            />
                        </div>
                        <div className="space-y-2">
                            <label htmlFor="description" className="block font-medium text-zinc-200">Descripción</label>
                            <textarea
                                id="description"
                                placeholder="Describe brevemente para qué sirve esta medicina..."
                                value={formData.description}
                                onChange={(e) => handleInputChange("description", e.target.value)}
                                rows={3}
                                maxLength={100}
                                required
                                className="w-full px-4 py-2 border border-zinc-700 bg-zinc-800 text-white placeholder-zinc-500 rounded-md focus:outline-none focus:ring-2 focus:ring-zinc-600"
                            />
                        </div>
                        <button
                            type="submit"
                            className="w-full bg-green-700 text-white py-2 rounded-md hover:bg-green-800 transition-colors"
                        >
                            Agregar Medicina
                        </button>
                    </form>
                </div>

                {/* Lista de medicinas */}
                <div className="space-y-4">
                    <h2 className="text-xl font-semibold text-white">Medicinas Agregadas</h2>

                    {medicineList === null ? (
                        <div className="rounded-2xl border border-zinc-800 bg-zinc-900">
                            <div className="flex items-center justify-center py-8">
                                <p className="text-zinc-400">No hay medicinas agregadas aún</p>
                            </div>
                        </div>
                    ) : (
                        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
                            {medicineList.map((medicine) => (
                                <div
                                    key={medicine.id}
                                    className="rounded-lg border border-zinc-800 bg-zinc-900 hover:shadow-md transition-shadow"
                                >
                                    <div className="p-6">
                                        <div className="space-y-3">
                                            <div className="flex items-center space-x-2">
                                                <Pill className="h-5 w-5 text-blue-500" />
                                                <span className="font-medium text-lg text-white">{medicine.name}</span>
                                            </div>
                                            <div className="flex items-start space-x-2">
                                                <FileText className="h-4 w-4 text-green-500 mt-1 flex-shrink-0" />
                                                <p className="text-sm text-zinc-400 leading-relaxed line-clamp-2">{medicine.description}</p>
                                            </div>
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
