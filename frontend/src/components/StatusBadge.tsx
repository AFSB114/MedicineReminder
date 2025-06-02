import React from 'react';
import { MedicineReminder } from '../types';

interface StatusBadgeProps {
  status: MedicineReminder['status'];
}

const StatusBadge: React.FC<StatusBadgeProps> = ({ status }) => {
  const getStatusStyles = () => {
    switch (status) {
      case 'CONFIRMED':
        return 'bg-emerald-500/20 text-emerald-400 border-emerald-500/50';
      case 'NOT_CONFIRMED':
        return 'bg-red-500/20 text-red-400 border-red-500/50';
      case 'PENDING':
        return 'bg-amber-500/20 text-amber-400 border-amber-500/50';
      case 'SENT':
        return 'bg-blue-500/20 text-blue-400 border-blue-500/50';
      default:
        return 'bg-gray-500/20 text-gray-400 border-gray-500/50';
    }
  };

  return (
    <span className={`px-2.5 py-0.5 rounded-full text-xs font-medium border ${getStatusStyles()} inline-flex items-center transition-colors duration-200`}>
      {status.charAt(0).toUpperCase() + status.slice(1)}
    </span>
  );
};

export default StatusBadge;