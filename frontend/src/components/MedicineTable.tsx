"use client"

import React, { useCallback, useEffect, useState } from 'react';
import { MedicineReminder } from '../types';
import TableHeader from './TableHeader';
import TableRow from './TableRow';
import { Search, RefreshCw, List } from 'lucide-react';

interface MedicineTableProps {
    reminders: MedicineReminder[];
}

const MedicineTable = () => {
    const [searchTerm, setSearchTerm] = useState('');
    const [isLoading, setIsLoading] = useState(false);
    const [reminderList, setReminderList] = useState<MedicineReminder[]>()
    const apiUrl = `${process.env.NEXT_PUBLIC_API_URL}/reminder`

    const getReminders = useCallback(async () => {
        try {
            const res = await fetch(`${apiUrl}/info`)
            if (!res.ok) throw new Error("No se pudo traer los datos")
            const list: MedicineReminder[] = await res.json()
            setReminderList(list)
        } catch (e) {
            console.error(e)
        }
    }, [apiUrl])

    useEffect(() => {
        getReminders()
    }, [getReminders])

    const handleRefresh = () => {
        setIsLoading(true);
        getReminders()
        setTimeout(() => {
            setIsLoading(false);
        }, 800);
    };

    const filteredReminders = reminderList?.filter(
        reminder =>
            reminder.firstName.toLowerCase().includes(searchTerm.toLowerCase()) ||
            reminder.email.toLowerCase().includes(searchTerm.toLowerCase()) ||
            reminder.medicine.toLowerCase().includes(searchTerm.toLowerCase())
    );

    return (
        <div className="flex flex-col m-auto mt-">
            <div className="flex flex-col sm:flex-row justify-between items-start sm:items-center mb-4 gap-4">
                <h2 className="text-xl font-semibold text-gray-100">Recordatorios de Medicamentos</h2>

                <div className="flex items-center space-x-4 w-full sm:w-auto">
                    <div className="relative w-full sm:w-64">
                        <div className="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
                            <Search size={18} className="text-gray-400" />
                        </div>
                        <input
                            type="text"
                            placeholder="Buscar recordatorios..."
                            className="w-full pl-10 pr-4 py-2 px-4 border border-zinc-700 bg-zinc-800 text-white placeholder-zinc-500 rounded-md focus:outline-none focus:ring-2 focus:ring-zinc-600"
                            value={searchTerm}
                            onChange={(e) => setSearchTerm(e.target.value)}
                        />
                    </div>

                    <button
                        className="p-2 bg-zinc-700 hover:bg-zinc-600 rounded-lg text-gray-200 transition-colors duration-200 flex items-center justify-center"
                        onClick={handleRefresh}
                    >
                        <RefreshCw size={18} className={`${isLoading ? 'animate-spin' : ''}`} />
                    </button>
                </div>
            </div>

            <div className="overflow-x-auto rounded-lg border border-gray-700 shadow-md">
                <table className="min-w-full divide-y divide-gray-700">
                    <thead className="bg-gray-900">
                        <tr>
                            <TableHeader label="Nombre" sortable />
                            <TableHeader label="Correo" sortable />
                            <TableHeader label="Hora Recordatorio" />
                            <TableHeader label="Medicina" sortable />
                            <TableHeader label="Primer Envío" />
                            <TableHeader label="Segundo Envío" />
                            <TableHeader label="Estado" sortable />
                        </tr>
                    </thead>
                    {filteredReminders && 
                        <tbody className="bg-gray-800 divide-y divide-gray-700">
                        {filteredReminders.length > 0 ? (
                            filteredReminders.map(reminder => (
                                <TableRow key={reminder.id} reminder={reminder} />
                            ))
                        ) : (
                            <tr>
                                <td colSpan={7} className="px-4 py-6 text-center text-gray-400">
                                    No se encontraron recordatorios
                                </td>
                            </tr>
                        )}
                    </tbody>
                    }
                </table>
            </div>

            {/*<div className="mt-4 text-sm text-gray-400">
                Mostrando {filteredReminders.length} de {reminderList.length} recordatorios
            </div>*/}
        </div>
    );
};

export default MedicineTable;