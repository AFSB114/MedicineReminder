"use client"

import Link from "next/link"
import { Home, ArrowLeft } from "lucide-react"

export function Navigation() {

    return (
        <div className="bg-zinc-900 border-b border-zinc-700 p-4">
            <div className="mx-auto max-w-4xl flex items-center justify-between">
                <Link href="/">
                    <button className="flex items-center text-zinc-200 hover:text-white">
                        <ArrowLeft className="mr-2 h-4 w-4" />
                        Volver al Inicio
                    </button>
                </Link>

                <div className="flex items-center space-x-2">
                    <Home className="h-4 w-4 text-zinc-100" />
                    <span className="text-sm text-zinc-200">Sistema de Gestión Médica</span>
                </div>
            </div>
        </div>

    )
}
