"use client"

import type React from "react"

import { useState } from "react"
import { Badge } from "@/ui/Badge"
import { User, Pill, Clock, Hash, X, Plus } from "lucide-react"
import usePrescription from "@/hook/prescription/usePrescription"
import { ListTimes, PrescriptionType } from "@/types"
import usePatient from "@/hook/patient/usePatient"
import useMedicine from "@/hook/medicine/useMedicine"

export default function prescriptionPage() {
  const { prescriptionList, addPrescription } = usePrescription()
  const { patientList } = usePatient()
  const { medicineList } = useMedicine()
  const [formData, setFormData] = useState({
    patient: "",
    medicine: "",
    dosage: "",
  })
  const [times, setTimes] = useState<string[]>([])
  const [currentSchedule, setCurrentSchedule] = useState("")

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault()
    console.log("Esta subiendo las datos")
    console.log(formData)

    if (formData.patient && formData.medicine && formData.dosage && times.length > 0) {
      const newPrescription: Omit<PrescriptionType, "id" | "suspended" | "suspendedDate" | "scheduleList"> & ListTimes = {
        patient: { id: parseInt(formData.patient) },
        medicine: { id: parseInt(formData.medicine) },
        dosage: Number.parseInt(formData.dosage),
        times: [...times],
      }

      console.log(newPrescription)

      addPrescription(newPrescription)
      setFormData({ patient: "", medicine: "", dosage: "" })
      setTimes([])
    }
  }

  const addSchedule = () => {
    if (currentSchedule && !times.includes(currentSchedule)) {
      setTimes([...times, currentSchedule])
      setCurrentSchedule("")
    }
  }

  const removeSchedule = (scheduleToRemove: string) => {
    setTimes(times.filter((schedule) => schedule !== scheduleToRemove))
  }


  return (
    <div className="min-h-screen bg-zinc-800 p-6">
      <div className="mx-auto max-w-4xl space-y-8">
        {/* Formulario */}
        <div className="w-full rounded-2xl border border-zinc-800 bg-zinc-900 p-6">
          <div className="text-2xl font-bold text-center text-white mb-4">
            Agregar Prescripción
          </div>
          <form onSubmit={handleSubmit} className="space-y-4 mt-4">
            <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
              <div className="space-y-2">
                <label htmlFor="patient" className="block font-medium text-zinc-200">Paciente</label>
                <select
                  id="patient"
                  value={formData.patient}
                  onChange={(e) => setFormData((prev) => ({ ...prev, patient: e.target.value }))}
                  required
                  className="w-full px-4 py-2 border border-zinc-700 bg-zinc-800 text-white placeholder-zinc-500 rounded-md focus:outline-none focus:ring-2 focus:ring-zinc-600"
                >
                  {/* Opciones */}
                  <option value="" disabled>-----</option>
                  {patientList?.map((patient) => (
                    <option key={patient.id} value={patient.id}>{patient.firstName} {patient.lastName}</option>
                  ))}
                </select>
              </div>
              <div className="space-y-2">
                <label htmlFor="medicine" className="block font-medium text-zinc-200">Medicina</label>
                <select
                  id="medicine"
                  value={formData.medicine}
                  onChange={(e) => setFormData((prev) => ({ ...prev, medicine: e.target.value }))}
                  required
                  className="w-full px-4 py-2 border border-zinc-700 bg-zinc-800 text-white placeholder-zinc-500 rounded-md focus:outline-none focus:ring-2 focus:ring-zinc-600"
                >
                  {/* Opciones */}
                  <option value="" disabled>-----</option>
                  {medicineList?.map((medicine) => (
                    <option key={medicine.id} value={medicine.id}>{medicine.name}</option>
                  ))}
                </select>
              </div>
            </div>

            <div className="space-y-2">
              <label htmlFor="quantity" className="block font-medium text-zinc-200">Cantidad por toma</label>
              <input
                id="quantity"
                type="number"
                placeholder="Ej: 1, 2, 0.5"
                value={formData.dosage}
                onChange={(e) => setFormData((prev) => ({ ...prev, dosage: e.target.value }))}
                min="0"
                step="0.5"
                required
                className="w-full px-4 py-2 border border-zinc-700 bg-zinc-800 text-white placeholder-zinc-500 rounded-md focus:outline-none focus:ring-2 focus:ring-zinc-600 appearance-none [-moz-appearance:textfield] [&::-webkit-inner-spin-button]:appearance-none [&::-webkit-outer-spin-button]:appearance-none"
              />
            </div>

            <div className="space-y-2">
              <label htmlFor="schedule" className="block font-medium text-zinc-200">Horarios del día</label>
              <div className="flex space-x-2">
                <input
                  id="schedule"
                  type="time"
                  value={currentSchedule}
                  onChange={(e) => setCurrentSchedule(e.target.value)}
                  className="px-4 py-2 border border-zinc-700 bg-zinc-800 text-white placeholder-zinc-500 rounded-md focus:outline-none focus:ring-2 focus:ring-zinc-600"
                />
                <button
                  type="button"
                  onClick={addSchedule}
                  className="rounded-md bg-green-700 p-2 text-white hover:bg-green-800 transition"
                >
                  <Plus className="h-4 w-4" />
                </button>
              </div>

              {times.length > 0 && (
                <div className="flex flex-wrap gap-2 mt-2">
                  {times.map((schedule) => (
                    <Badge key={schedule} className="flex items-center gap-1 bg-zinc-800 text-white">
                      {schedule}
                      <button
                        type="button"
                        onClick={() => removeSchedule(schedule)}
                        className="ml-1 hover:bg-red-600 rounded-full p-0.5"
                      >
                        <X className="h-3 w-3" />
                      </button>
                    </Badge>
                  ))}
                </div>
              )}
            </div>

            <button
              type="submit"
              disabled={times.length === 0}
              className="w-full bg-green-700 text-white py-2 rounded-md hover:bg-green-800 transition-colors"
            >
              Agregar Prescripción
            </button>
          </form>
        </div>

        {/* Lista de prescripciones */}
        <div className="space-y-4">
          <h2 className="text-xl font-semibold text-white">Prescripciones Agregadas</h2>

          {prescriptionList === null ? (
            <div className="rounded-2xl border border-zinc-800 bg-zinc-900">
              <div className="flex items-center justify-center py-8">
                <p className="text-zinc-400">No hay prescripciones agregadas aún</p>
              </div>
            </div>
          ) : (
            <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
              {prescriptionList.map((prescription) => (
                <div key={prescription.id} className="rounded-lg border border-zinc-800 bg-zinc-900 hover:shadow-md transition-shadow">
                  <div className="p-6 space-y-3">
                    <div className="flex items-center space-x-2">
                      <User className="h-4 w-4 text-blue-500" />
                      <span className="font-medium text-white">{prescription.patient.firstName} {prescription.patient.lastName}</span>
                    </div>
                    <div className="flex items-center space-x-2">
                      <Pill className="h-4 w-4 text-green-500" />
                      <span className="text-sm text-white">{prescription.medicine.name}</span>
                    </div>
                    <div className="flex items-center space-x-2">
                      <Hash className="h-4 w-4 text-orange-500" />
                      <span className="text-sm text-white">
                        Cantidad: {prescription.dosage} {prescription.dosage === 1 ? "unidad" : "unidades"}
                      </span>
                    </div>
                    <div className="space-y-2">
                      <div className="flex items-center space-x-2">
                        <Clock className="h-4 w-4 text-purple-500" />
                        <span className="text-sm font-medium text-white">Horarios:</span>
                      </div>
                      <div className="flex flex-wrap gap-1 ml-6">
                        {prescription.scheduleList.map((schedule) => (
                          <Badge key={schedule.id} className="text-xs bg-zinc-800 text-white">
                            {schedule.time.slice(0, 5)}
                          </Badge>
                        ))}
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
