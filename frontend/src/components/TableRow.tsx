import React from 'react';
import { MedicineReminder } from '../types';
import StatusBadge from './StatusBadge';

interface TableRowProps {
    reminder: MedicineReminder;
}

const TableRow: React.FC<TableRowProps> = ({ reminder }) => {
    return (
        <tr className="bg-zinc-900 hover:bg-zinc-800 transition-colors duration-200">
            <td className="px-4 py-3 whitespace-nowrap text-sm font-medium text-gray-200">
                {reminder.firstName} {reminder.lastName}
            </td>
            <td className="px-4 py-3 whitespace-nowrap text-sm text-gray-300">
                {reminder.email}
            </td>
            <td className="px-4 py-3 whitespace-nowrap text-sm text-gray-300">
                {reminder.scheduleTime}
            </td>
            <td className="px-4 py-3 whitespace-nowrap text-sm text-gray-300">
                {reminder.medicine}
            </td>
            <td className="px-4 py-3 whitespace-nowrap text-sm text-gray-300">
                {reminder.sentTime}
            </td>
            <td className="px-4 py-3 whitespace-nowrap text-sm text-gray-300">
                {reminder.secondSentTime}
            </td>
            <td className="px-4 py-3 whitespace-nowrap text-sm">
                <StatusBadge status={reminder.status} />
            </td>
        </tr>
    );
};

export default TableRow;