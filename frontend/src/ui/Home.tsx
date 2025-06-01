"use client"

import Link from "next/link"
import { User, Pill, FileText, Bell, ArrowRight } from "lucide-react"

export default function HomePage() {
    const navigationItems = [
        {
            title: "Gestión de Pacientes",
            description: "Agregar y visualizar información de pacientes con nombre, apellido y correo electrónico",
            icon: User,
            href: "/sections/patient",
            color: "text-blue-600",
            bgColor: "bg-blue-50",
        },
        {
            title: "Inventario de Medicinas",
            description: "Registrar medicinas con nombre y descripción para mantener un inventario actualizado",
            icon: Pill,
            href: "/sections/medicine",
            color: "text-green-600",
            bgColor: "bg-green-50",
        },
        {
            title: "Prescripciones Médicas",
            description: "Crear prescripciones asignando medicinas a pacientes con horarios y cantidades específicas",
            icon: FileText,
            href: "/sections/prescription",
            color: "text-purple-600",
            bgColor: "bg-purple-50",
        },
        {
            title: "Historial de Recordatorios",
            description: "Visualizar y gestionar todos los recordatorios enviados a los pacientes sobre sus medicinas",
            icon: Bell,
            href: "/sections/reminder",
            color: "text-orange-600",
            bgColor: "bg-orange-50",
        },
    ]

    return (
        <div className="min-h-screen bg-zinc-900 p-6 text-zinc-100">
            <div className="mx-auto max-w-6xl space-y-8">
                {/* Header */}
                <div className="text-center space-y-4">
                    <h1 className="text-4xl font-bold text-white">Sistema de Gestión Médica</h1>
                    <p className="text-lg text-zinc-400 max-w-2xl mx-auto">
                        Administra contactos, medicinas, prescripciones y recordatorios de manera eficiente y organizada
                    </p>
                </div>

                {/* Navigation Cards */}
                <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-2 gap-6">
                    {navigationItems.map((item) => {
                        const IconComponent = item.icon
                        return (
                            <div key={item.href} className="hover:shadow-lg transition-all duration-300 hover:-translate-y-1 bg-zinc-800 rounded-lg p-6">
                                <div className="text-center">
                                    <div
                                        className={`mx-auto w-16 h-16 ${item.bgColor} rounded-full flex items-center justify-center mb-4`}
                                    >
                                        <IconComponent className={`h-8 w-8 ${item.color}`} />
                                    </div>
                                    <div className="text-xl text-white">{item.title}</div>
                                    <div className="text-sm leading-relaxed text-zinc-300">{item.description}</div>
                                </div>
                                <div className="mt-4">
                                    <Link href={item.href}>
                                        <button className="w-full group text-blue-400 hover:text-blue-300 flex items-center justify-center">
                                            Acceder
                                            <ArrowRight className="ml-2 h-4 w-4 transition-transform group-hover:translate-x-1" />
                                        </button>
                                    </Link>
                                </div>
                            </div>
                        )
                    })}
                </div>

                {/* Quick Stats */}
                <div className="bg-zinc-800 rounded-lg p-6 shadow-sm">
                    <h2 className="text-xl font-semibold mb-4 text-white">Características del Sistema</h2>
                    <div className="grid grid-cols-1 md:grid-cols-4 gap-4">
                        <div className="text-center p-4">
                            <div className="text-2xl font-bold text-blue-500">✓</div>
                            <h3 className="font-medium text-white">Fácil de Usar</h3>
                            <p className="text-sm text-zinc-400">Interfaz intuitiva y amigable</p>
                        </div>
                        <div className="text-center p-4">
                            <div className="text-2xl font-bold text-green-500">✓</div>
                            <h3 className="font-medium text-white">Organizado</h3>
                            <p className="text-sm text-zinc-400">Información bien estructurada</p>
                        </div>
                        <div className="text-center p-4">
                            <div className="text-2xl font-bold text-purple-500">✓</div>
                            <h3 className="font-medium text-white">Responsive</h3>
                            <p className="text-sm text-zinc-400">Funciona en todos los dispositivos</p>
                        </div>
                        <div className="text-center p-4">
                            <div className="text-2xl font-bold text-orange-500">✓</div>
                            <h3 className="font-medium text-white">Recordatorios</h3>
                            <p className="text-sm text-zinc-400">Sistema de notificaciones</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}
